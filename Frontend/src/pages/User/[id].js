import React, { useState, useEffect } from "react";
import { useNavigate, useLocation, Link } from "react-router-dom";
import { HeartFill, Heart } from "react-bootstrap-icons";
import Navbar from "../../components/Navbar";
export default function User() {
  const navigate = useNavigate();
  const [about, setAbout] = useState("");
  const [name, setName] = useState("");
  const [loggedIn, setLoggedIn] = useState(false);
  const [favorite, setFavorite] = useState(false);
  const [entries, setEntries] = useState([]);
  const location = useLocation();
  function handleNavigate(bookid, entryid) {
    window.location = "/book/" + bookid + "/entry/" + entryid;
  }
  const handleEntries = async () => {
    const axios = require("axios");
    axios
      .get("/api/entry/getByUserId?user_id=" + location.pathname.split("/")[2])
      .then(async (response) => {
        setEntries(response.data);
      });
  };

  const isFavoriteUser = () => {
    const axios = require("axios");
    axios
      .get(
        "/api/favoriteuser/isfavoriteuser?user_id=" +
          window.localStorage.getItem("user_id") +
          "&favorite_user_id=" +
          location.pathname.split("/")[2]
      )
      .then(function (response) {
        setFavorite(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  };
  const handleFavorite = () => {
    const axios = require("axios");
    if (!favorite) {
      var data = JSON.stringify({
        userid: window.localStorage.getItem("user_id"),
        favoriteuserid: location.pathname.split("/")[2],
      });

      var config = {
        method: "post",
        url: "/api/favoriteuser",
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
      data = JSON.stringify({
        userid: window.localStorage.getItem("user_id"),
        favoriteuserid: location.pathname.split("/")[2],
      });

      config = {
        method: "post",
        url: "/api/favoriteuser/delete",
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
    const axios = require("axios");
    handleEntries();
    axios
      .post("/api/user/getUser", {
        user_id: location.pathname.split("/")[2],
        token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
      })
      .then(function (response) {
        setName(response.data.name);
        setAbout(response.data.about);
      })
      .catch(function (error) {
        console.log(error);
      });

    isFavoriteUser();
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
            <div className="d-table-cell p-4 col-12 col-sm-4 col-md-3 leftBar align-items-center justify-items-center">
              <div>
                <img
                  alt="author"
                  style={{
                    maxWidth: "80%",
                    maxHeight: "80%",
                    objectFit: "cover",
                  }}
                  src={"/api/user/photo/" + location.pathname.split("/")[2]}
                />
              </div>
            </div>
            <div className="col-12 col-sm-7 col-md-8 rightSide">
              <div className="about">Kullanıcı Hakkında</div>
              <p className="aboutIcerik">{about}</p>
              {entries?.length > 0 ? (
                <div className="about mt-4 mb-2">Blog Gönderileri</div>
              ) : (
                <></>
              )}
              {entries?.map((entry) => {
                return (
                  <div
                    key={entry.entry_id}
                    className="entryCard mb-3"
                    onClick={() => handleNavigate(entry.bookid, entry.entry_id)}
                  >
                    <div className="ellipsis header">{entry.header}</div>
                    <div className="ellipsis">{entry.entry}</div>
                  </div>
                );
              })}
            </div>
          </div>
        </div>
      </>
    );
}
