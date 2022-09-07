import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import Card from "../components/Card";
export default function FavoriteAuthors() {
  const [authors, setAuthors] = useState([]);
  const [loggedIn, setLoggedIn] = useState(false);
  const [search, setSearch] = useState("");
  const navigate = useNavigate();
  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (!window.localStorage.getItem("loggedIn"))
      navigate("login", { replace: true });
    const axios = require("axios");
    axios
      .get(
        "/api/favoriteauthor/?user_id=" + window.localStorage.getItem("user_id")
      )
      .then((response) => response.data)
      .then((data) => setAuthors(data));
  }, [navigate]);
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
          <input
            type="text"
            className="form-control mb-3"
            style={{ width: "min(250px, 100vw)" }}
            placeholder="Yazar ara"
            onChange={(e) => setSearch(e.target.value)}
          />
          <div className="row">
            {authors
              ?.filter((author) =>
                turkishToLower(author.name)
                  .trim()
                  .includes(turkishToLower(search).trim())
              )
              .map((author) => {
                return (
                  <Card key={author.author_id} type="author" data={author} />
                );
              })}
          </div>
        </div>
      </>
    );
}
