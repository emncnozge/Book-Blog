import axios from "axios";
import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Navbar from "../../components/Navbar";
export default function Book() {
  const navigate = useNavigate();
  const [about, setAbout] = useState("");
  const [name, setName] = useState("");
  const [book_id, setBook_id] = useState(0);
  const [loggedIn, setLoggedIn] = useState(false);
  const location = useLocation();

  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (!window.localStorage.getItem("loggedIn"))
      navigate("login", { replace: true });
    setBook_id(location.pathname.split("/")[2]);
    const axios = require("axios");
    axios
      .post("/api/book/getBook", {
        book_id: location.pathname.split("/")[2],
      })
      .then(function (response) {
        setBook_id(response.data.book_id);
        setName(response.data.name);
        setAbout(response.data.about);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);

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
                  alt="book"
                  style={{
                    maxWidth: "80%",
                    maxHeight: "80%",
                    objectFit: "cover",
                  }}
                  src={"http://localhost:8080/api/book/photo/" + book_id}
                />
              </div>
            </div>
            <div className="col-12 col-sm-7 col-md-8">
              <div className="about">Kitap Hakkında</div>
              <p className="aboutIcerik">{about}</p>
            </div>
          </div>
        </div>
      </>
    );
}
