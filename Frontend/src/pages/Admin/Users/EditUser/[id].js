import axios from "axios";
import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Navbar from "../../../../components/Navbar";
export default function Profile() {
  const navigate = useNavigate();
  const [user_id, setUser_id] = useState("");
  const [about, setAbout] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [isAdmin, setIsAdmin] = useState(false);
  const [loggedIn, setLoggedIn] = useState(false);
  const location = useLocation();
  const [success, setSuccess] = useState(false);
  const [error, setError] = useState(false);
  const deleteAccount = async (e) => {
    await axios
      .delete("/api/user/deleteUser", {
        data: {
          user_id: location.pathname.split("/")[3],
          token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
        },
      })
      .then(() => {
        window.localStorage.removeItem("user_id");
        window.localStorage.removeItem("loggedIn");
        window.localStorage.removeItem("name");
        navigate("/login", { replace: true });
      });
  };

  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (!window.localStorage.getItem("loggedIn"))
      navigate("login", { replace: true });
    setUser_id(location.pathname.split("/")[3]);
    const axios = require("axios");
    axios
      .post("/api/user/getUser", {
        user_id: location.pathname.split("/")[3],
        token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
      })
      .then(function (response) {
        setAbout(response.data.about);
        setEmail(response.data.email);
        setName(response.data.name);
        setIsAdmin(response.data.is_admin);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [navigate]);

  const handleName = (e) => setName(e.target.value);
  const handleEmail = (e) => setEmail(e.target.value);
  const handlePassword = (e) => setPassword(e.target.value);
  const handleAbout = (e) => setAbout(e.target.value);
  const handleIsAdmin = (e) => setIsAdmin(e.target.checked);
  async function save() {
    axios
      .put("/api/user/updateUser", {
        user_id: location.pathname.split("/")[3],
        name,
        email,
        password,
        about,
        isAdmin,
        token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
      })
      .then((response) => {
        if (response.data) {
          setError(false);
          setSuccess(true);
        } else {
          setError(true);
          setSuccess(false);
        }
      });
  }
  if (loggedIn)
    return (
      <>
        <Navbar />
        <div className="container-fluid">
          <div className="row ">
            <div className="d-table-cell my-auto p-4 col-12 col-sm-4 col-md-3 leftBar align-items-center justify-items-center">
              <div>
                <div className="underPhoto mb-2 ">Profil Fotoğrafı</div>
                <img
                  style={{
                    maxWidth: "100%",
                    maxHeight: "100%",
                    objectFit: "cover",
                    border: "2px solid #ccc",
                    borderRadius: ".3rem",
                    padding: "0.1rem",
                    marginBottom: "1rem",
                  }}
                  alt="profile_photo"
                  src={"/api/user/photo/" + user_id}
                />

                <form
                  action="/api/user/photo"
                  name="form"
                  method="post"
                  encType="multipart/form-data"
                >
                  <div>
                    <input
                      type="text"
                      name="user_id"
                      defaultValue={user_id}
                      hidden
                    />
                    <input
                      type="file"
                      className="form-control form-control-sm"
                      name="image"
                      accept="image/png"
                    />
                  </div>
                  <input
                    type="submit"
                    className="btn btn-success upload"
                    value="Kaydet"
                  />
                </form>
              </div>
            </div>
            <div className="col-12 col-sm-7 col-md-8">
              <div className="underPhoto">Kişisel Bilgiler</div>

              <p className="my-auto label">Ad-Soyad</p>
              <input
                name="name"
                type="text"
                value={name}
                onChange={handleName}
                className="form-control"
              />
              <p className="my-auto label mt-2">E-posta</p>
              <input
                name="name"
                type="email"
                value={email}
                onChange={handleEmail}
                className="form-control"
              />
              <p className="my-auto label mt-2">Şifre:</p>
              <input
                name="name"
                type="password"
                value={password}
                onChange={handlePassword}
                className="form-control"
              />
              <p className="my-auto label mt-2">Hakkında</p>
              <textarea
                name="name"
                value={about}
                onChange={handleAbout}
                className="form-control"
                style={{ height: "40vh" }}
              />
              <div className="form-check mt-3">
                <input
                  className="form-check-input"
                  type="checkbox"
                  value={isAdmin}
                  checked={isAdmin}
                  onChange={handleIsAdmin}
                />
                <label className="form-check-label" for="flexCheckDefault">
                  Admin
                </label>
              </div>

              <div className="mt-3 mb-4" style={{ float: "right" }}>
                <input
                  type="button"
                  className="btn btn-success"
                  value="Kaydet"
                  onClick={save}
                />
                {success ? (
                  <div style={{ textAlign: "center", color: "green" }}>
                    Başarılı!
                  </div>
                ) : (
                  <></>
                )}
                {error ? (
                  <div style={{ textAlign: "center", color: "red" }}>
                    Başarısız!
                  </div>
                ) : (
                  <></>
                )}
              </div>
              <div className="mt-3 mb-4">
                <input
                  type="button"
                  className="btn btn-danger"
                  value="Hesabı sil"
                  onClick={deleteAccount}
                />
              </div>
            </div>
          </div>
        </div>
      </>
    );
}
