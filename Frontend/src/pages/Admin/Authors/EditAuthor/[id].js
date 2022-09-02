import React, { useState, useEffect } from "react";
import { HeartFill, Heart } from "react-bootstrap-icons";
import { useNavigate, useLocation } from "react-router-dom";
import Navbar from "../../../../components/Navbar";
export default function Author() {
  const navigate = useNavigate();
  const [about, setAbout] = useState("");
  const [name, setName] = useState("");
  const [author_id, setAuthor_id] = useState(0);
  const [loggedIn, setLoggedIn] = useState(false);
  const location = useLocation();
  const handleName = (e) => setName(e.target.value);
  const handleAbout = (e) => setAbout(e.target.value);

  async function save() {
    const axios = require("axios");
    axios.put("/api/author/" + location.pathname.split("/")[3], {
      name,
      about,
    });
  }

  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (
      !window.localStorage.getItem("loggedIn") &&
      !window.localStorage.getItem("isAdmin")
    )
      navigate("login", { replace: true });
    setAuthor_id(location.pathname.split("/")[3]);
    const axios = require("axios");
    axios
      .post("/api/author/getAuthor", {
        author_id: location.pathname.split("/")[3],
      })
      .then(function (response) {
        setAuthor_id(response.data.author_id);
        setName(response.data.name);
        setAbout(response.data.about);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [navigate, location.pathname]);

  if (loggedIn)
    return (
      <>
        <Navbar />
        <div className="container-fluid">
          <div className="row">
            <div className="d-table-cell p-4 col-12 col-sm-4 col-md-3 leftBar align-items-center justify-items-center">
              <div>
                <img
                  alt="author"
                  style={{
                    maxWidth: "100%",
                    maxHeight: "100%",
                    objectFit: "cover",
                    border: "2px solid #ccc",
                    borderRadius: ".3rem",
                    padding: "0.1rem",
                    marginBottom: "1rem",
                  }}
                  src={"/api/author/photo/" + author_id}
                />
                <form
                  action="/api/author/photo"
                  name="form"
                  method="post"
                  encType="multipart/form-data"
                >
                  <div>
                    <input
                      type="text"
                      name="author_id"
                      defaultValue={author_id}
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
            <div className="col-12 col-sm-7 col-md-8 rightSide">
              <div className="about mb-3">Yazar Ad-Soyad</div>
              <input
                type="text"
                className="form-control form-control-lg aboutIcerik mb-3"
                value={name}
                onChange={handleName}
              ></input>
              <div className="about">Yazar Hakkında</div>
              <textarea
                className="aboutIcerik form-control"
                value={about}
                onChange={handleAbout}
                style={{ height: "40vh" }}
              />
              <div className="mt-2 mb-4" style={{ float: "right" }}>
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
