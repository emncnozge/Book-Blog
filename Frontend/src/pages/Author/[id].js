import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Navbar from "../../components/Navbar";
export default function Book() {
  const navigate = useNavigate();
  const [about, setAbout] = useState("");
  const [name, setName] = useState("");
  const [author_id, setAuthor_id] = useState(0);
  const [loggedIn, setLoggedIn] = useState(false);
  const location = useLocation();

  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (!window.localStorage.getItem("loggedIn"))
      navigate("login", { replace: true });
    setAuthor_id(location.pathname.split("/")[2]);
    const axios = require("axios");
    axios
      .post("/api/author/getAuthor", {
        author_id: location.pathname.split("/")[2],
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
          <div className="row ">
            <p className="baslik">{name}</p>
            <div className="d-table-cell my-auto p-4 col-12 col-sm-4 col-md-3 leftBar align-items-center justify-items-center">
              <div>
                <img
                  alt="author"
                  style={{
                    maxWidth: "80%",
                    maxHeight: "80%",
                    objectFit: "cover",
                  }}
                  src={"/api/author/photo/" + author_id}
                />
              </div>
            </div>
            <div className="col-12 col-sm-7 col-md-8">
              <div className="about">Yazar Hakkında</div>
              <p className="aboutIcerik">{about}</p>
            </div>
          </div>
        </div>
      </>
    );
}
