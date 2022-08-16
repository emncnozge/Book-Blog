import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
export default function MainPage() {
  const [authors, setAuthors] = useState([]);
  const [loggedIn, setLoggedIn] = useState(false);
  const navigate = useNavigate();
  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (!window.localStorage.getItem("loggedIn"))
      navigate("login", { replace: true });
    const axios = require("axios");
    axios
      .get("http://localhost:8080/api/author/")
      .then((response) => response.data)
      .then((data) => setAuthors(data));
  }, []);
  if (loggedIn)
    return (
      <>
        <Navbar />
        <div className="container-fluid">
          <div className="row">
            {authors?.map((author) => {
              return (
                <div
                  key={author.author_id}
                  className="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2"
                >
                  <Link to={author.author_id} className="author">
                    <div className="card">
                      <div className="card-img-top mt-3">
                        <img
                          alt="author_photo"
                          src={
                            "http://localhost:8080/api/author/photo/" +
                            author.book_id
                          }
                          className="image"
                        />
                      </div>

                      <div className="card-body">
                        <h5 className="card-title">{author.name}</h5>
                      </div>
                    </div>
                  </Link>
                </div>
              );
            })}
          </div>
        </div>
      </>
    );
}
