import React, { useState, useEffect } from "react";
import { HeartFill, Heart } from "react-bootstrap-icons";
import { useNavigate, useLocation } from "react-router-dom";
import Navbar from "../../components/Navbar";
import Card from "../../components/Card";
export default function Author() {
  const navigate = useNavigate();
  const [about, setAbout] = useState("");
  const [books, setBooks] = useState([]);
  const [name, setName] = useState("");
  const [author_id, setAuthor_id] = useState(0);
  const [loggedIn, setLoggedIn] = useState(false);
  const [favorite, setFavorite] = useState(false);
  const location = useLocation();
  const [search, setSearch] = useState("");

  const handleFavorite = () => {
    const axios = require("axios");
    if (!favorite) {
      var data = JSON.stringify({
        userid: window.localStorage.getItem("user_id"),
        favoriteauthorid: location.pathname.split("/")[2],
      });

      var config = {
        method: "post",
        url: "/api/favoriteauthor",
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
        favoriteauthorid: location.pathname.split("/")[2],
      });

      var config = {
        method: "post",
        url: "/api/favoriteauthor/delete",
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
    axios
      .post("/api/book/getBooksByAuthorId", {
        author_id: location.pathname.split("/")[2],
      })
      .then(function (response) {
        console.log(response.data);
        setBooks(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });

    axios
      .get(
        "/api/favoriteauthor/isfavoriteauthor?user_id=" +
          window.localStorage.getItem("user_id") +
          "&favorite_author_id=" +
          location.pathname.split("/")[2]
      )
      .then(function (response) {
        setFavorite(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [navigate, location.pathname]);
  function turkishToLower(e) {
    let string = e;
    let letters = { İ: "i", I: "ı", Ş: "ş", Ğ: "ğ", Ü: "ü", Ö: "ö", Ç: "ç" };
    string = string.replace(/(([İIŞĞÜÇÖ]))/g, function (letter) {
      return letters[letter];
    });
    return string.toLowerCase();
  }
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
          <div className="row ">
            <div className="d-table-cell p-4 col-12 col-sm-4 col-md-3 leftBar align-items-center justify-items-center">
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
            <div className="col-12 col-sm-7 col-md-8 rightSide">
              <div className="about">Yazar Hakkında</div>
              <p className="aboutIcerik">{about}</p>
            </div>
            <div className="about">Kitaplar</div>
            <input
              type="text"
              className="form-control mb-3"
              style={{ width: "min(250px, 100vw)" }}
              placeholder="Kitap ara"
              onChange={(e) => setSearch(e.target.value)}
            />
            <div className="row">
              {books
                ?.filter((book) =>
                  turkishToLower(book.name)
                    .trim()
                    .includes(turkishToLower(search).trim())
                )
                .map((book) => {
                  return <Card key={book.book_id} type="book" data={book} />;
                })}
            </div>
          </div>
        </div>
      </>
    );
}
