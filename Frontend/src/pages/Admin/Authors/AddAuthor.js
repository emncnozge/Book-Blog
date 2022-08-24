import axios from "axios";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../../../components/Navbar";
export default function Profile() {
  const navigate = useNavigate();
  const [about, setAbout] = useState("");
  const [name, setName] = useState("");
  const [loggedIn, setLoggedIn] = useState(false);

  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (
      !window.localStorage.getItem("loggedIn") &&
      !window.localStorage.getItem("isAdmin")
    )
      navigate("login", { replace: true });
  }, [navigate]);

  const handleName = (e) => setName(e.target.value);
  const handleAbout = (e) => setAbout(e.target.value);

  async function save() {
    axios
      .post("/api/author", {
        name,
        about,
        photo_url: "",
      })
      .then((response) => navigate("../admin/editauthor/" + response.data));
  }
  if (loggedIn && window.localStorage.getItem("isAdmin"))
    return (
      <>
        <Navbar />
        <div className="container-fluid">
          <div className="row ">
            <div className="col">
              <div className="underPhoto">Yazar Bilgileri</div>

              <p className="my-auto label">Ad-Soyad</p>
              <input
                name="name"
                type="text"
                value={name}
                onChange={handleName}
                className="form-control"
              />
              <p className="my-auto label mt-2">Hakkında</p>
              <textarea
                name="name"
                value={about}
                onChange={handleAbout}
                className="form-control"
              />

              <div className="mt-2 mb-4">
                <input
                  type="button"
                  className="btn btn-success"
                  value="Kaydet"
                  onClick={save}
                />
              </div>
            </div>
          </div>
        </div>
      </>
    );
}
