import axios from "axios";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
export default function MainPage() {
  const navigate = useNavigate();
  const [user_id, setUser_id] = useState("");
  const [about, setAbout] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [loggedIn, setLoggedIn] = useState(false);

  const deleteAccount = async (e) => {
    await axios
      .delete("/api/user/deleteUser", {
        data: {
          user_id: window.localStorage.getItem("user_id"),
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
    setUser_id(window.localStorage.getItem("user_id"));
    const axios = require("axios");
    axios
      .post("/api/user/getUser", {
        user_id: window.localStorage.getItem("user_id"),
        token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
      })
      .then(function (response) {
        setAbout(response.data.about);
        setEmail(response.data.email);
        setName(response.data.name);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);

  const handleName = (e) => setName(e.target.value);
  const handleEmail = (e) => setEmail(e.target.value);
  const handlePassword = (e) => setPassword(e.target.value);
  const handleAbout = (e) => setAbout(e.target.value);

  async function save() {
    axios.put("/api/user/updateUser", {
      user_id: window.localStorage.getItem("user_id"),
      name,
      email,
      password,
      about,
      token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
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
                  src={"http://localhost:8080/api/user/photo/" + user_id}
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
              />

              <div className="mt-2 mb-4" style={{ float: "right" }}>
                <input
                  type="button"
                  className="btn btn-success"
                  value="Kaydet"
                  onClick={save}
                />
              </div>
              <div className="mt-2 mb-4">
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
