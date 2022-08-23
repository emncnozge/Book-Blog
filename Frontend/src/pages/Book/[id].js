import React, { useState, useEffect } from "react";
import { HeartFill, Heart } from "react-bootstrap-icons";
import { useNavigate, useLocation } from "react-router-dom";
import Navbar from "../../components/Navbar";
export default function Book() {
  const navigate = useNavigate();
  const [about, setAbout] = useState("");
  const [name, setName] = useState("");
  const [book_id, setBook_id] = useState(0);
  const [loggedIn, setLoggedIn] = useState(false);
  const [favorite, setFavorite] = useState(false);
  const location = useLocation();

  const handleFavorite = () => {
    const axios = require("axios");
    if (!favorite) {
      var data = JSON.stringify({
        userid: window.localStorage.getItem("user_id"),
        favoritebookid: location.pathname.split("/")[2],
      });

      var config = {
        method: "post",
        url: "/api/favoritebook",
        headers: {
          "Content-Type": "application/json",
        },
        data: data,
      };

      axios(config)
        .then(function (response) {
          setFavorite(response);
        })
        .catch(function (error) {
          console.log(error);
        });
    } else {
      var data = JSON.stringify({
        userid: window.localStorage.getItem("user_id"),
        favoritebookid: location.pathname.split("/")[2],
      });

      var config = {
        method: "post",
        url: "/api/favoritebook/delete",
        headers: {
          "Content-Type": "application/json",
        },
        data: data,
      };

      axios(config)
        .then(function (response) {
          setFavorite(!response);
        })
        .catch(function (error) {
          console.log(error);
        });
    }
  };
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
    axios
      .get(
        "/api/favoritebook/isfavoritebook?user_id=" +
          window.localStorage.getItem("user_id") +
          "&favorite_book_id=" +
          location.pathname.split("/")[2]
      )
      .then(function (response) {
        setFavorite(response.data);
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
          <p className="baslik">{name}</p>
          <button
            id="favorite"
            className="btn favorite"
            onClick={handleFavorite}
          >
            {favorite ? (
              <HeartFill style={{ marginBottom: "4px" }}></HeartFill>
            ) : (
              <Heart style={{ marginBottom: "4px" }}></Heart>
            )}
          </button>
          <div className="row">
            <div className="d-table-cell my-auto p-4 col-12 col-sm-4 col-md-3 leftBar align-items-center justify-items-center">
              <div>
                <img
                  alt="book"
                  style={{
                    maxWidth: "80%",
                    maxHeight: "80%",
                    objectFit: "cover",
                  }}
                  src={"/api/book/photo/" + book_id}
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
