import React, { useState, useEffect } from "react";
import { HeartFill, Heart, Plus } from "react-bootstrap-icons";
import { useNavigate, useLocation, Link } from "react-router-dom";
import Navbar from "../../components/Navbar";
export default function Book() {
  const navigate = useNavigate();
  const [about, setAbout] = useState("");
  const [name, setName] = useState("");
  const [book_id, setBook_id] = useState(0);
  const [loggedIn, setLoggedIn] = useState(false);
  const [favorite, setFavorite] = useState(false);
  const [entries, setEntries] = useState([]);
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
      data = JSON.stringify({
        userid: window.localStorage.getItem("user_id"),
        favoritebookid: location.pathname.split("/")[2],
      });

      config = {
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

  const handleEntries = async () => {
    const axios = require("axios");
    axios
      .get(
        "/api/entry/getTop5ByBookId?book_id=" + location.pathname.split("/")[2]
      )
      .then(async (response) => {
        var newEntries = response.data;
        for (let i = 0; i < newEntries.length; i++) {
          await axios
            .post("/api/user/getUser", {
              user_id: newEntries[i].userid,
              token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
            })
            .then((response2) => {
              newEntries[i].name = response2.data.name;
            });
        }
        setEntries(response.data);
      });
  };
  const isFavorite = () => {
    const axios = require("axios");
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
  };
  function handleNavigate(bookid, entryid) {
    navigate("/book/" + bookid + "/entry/" + entryid);
  }
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
    isFavorite();
    handleEntries();
  }, [navigate, location.pathname]);

  if (loggedIn)
    return (
      <>
        <Navbar />
        <div className="container-fluid">
          <p className="baslik">{name}</p>
          <Link to={"/addentry/" + book_id} className="btn add ms-4">
            <Plus size={40}></Plus>
          </Link>
          <button
            id="favorite"
            className="btn favorite"
            onClick={handleFavorite}
          >
            {favorite ? (
              <HeartFill
                style={{ marginBottom: "4px", marginRight: "1px" }}
              ></HeartFill>
            ) : (
              <Heart
                style={{ marginBottom: "4px", marginRight: "1px" }}
              ></Heart>
            )}
          </button>
          <div className="row">
            <div className="d-table-cell p-4 col-12 col-sm-4 col-md-3 leftBar align-items-center justify-items-center">
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
            <div className="col-12 col-sm-7 col-md-8 rightSide">
              <div className="about">Kitap Hakkında</div>
              <p className="aboutIcerik">{about}</p>
              {entries?.length > 0 ? (
                <>
                  <div className="about mt-4 mb-0">Son Blog Gönderileri</div>
                  <div className="all mb-2">
                    <Link
                      to={
                        "/book/" + location.pathname.split("/")[2] + "/entries"
                      }
                      className="all"
                    >
                      Tüm Gönderileri Gör
                    </Link>
                  </div>
                </>
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
                    <div className="ellipsis name">- {entry.name}</div>
                  </div>
                );
              })}
            </div>
          </div>
        </div>
      </>
    );
}
