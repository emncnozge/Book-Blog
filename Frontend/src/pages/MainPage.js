import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
export default function MainPage() {
  const navigate = useNavigate();
  const [books, setBooks] = useState([]);
  const [user_id, setUser_id] = useState(0);
  const [name, setName] = useState("");
  const [photo, setPhoto] = useState();
  const [loggedIn, setLoggedIn] = useState(false);
  const handlePhoto = (e) => {
    setPhoto(e.target.files[0]);
  };
  const changePhoto = () => {
    const axios = require("axios");
    axios.post({
      url: "http://localhost:8080/api/user/photo/",
      data: { user_id: user_id, image: photo },
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  };
  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (!window.localStorage.getItem("loggedIn"))
      navigate("login", { replace: true });
    const axios = require("axios");
    axios
      .get("http://localhost:8080/api/book/last20")
      .then((response) => response.data)
      .then((data) => setBooks(data));
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    setUser_id(window.localStorage.getItem("user_id"));
    setName(window.localStorage.getItem("name"));
  }, []);
  if (loggedIn)
    return (
      <>
        <Navbar />
        <div className="container-fluid">
          <div className="row">
            <p className="altbaslik">Son eklenenler</p>
            {books?.map((book) => {
              return (
                <div
                  key={book.book_id}
                  className="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2"
                >
                  <Link to={"/book/" + book.book_id} className="book">
                    <div className="card">
                      <div className="card-img-top mt-3">
                        <img
                          alt="book_photo"
                          src={
                            "http://localhost:8080/api/book/photo/" +
                            book.book_id
                          }
                          className="image"
                        />
                      </div>

                      <div className="card-body">
                        <h5 className="card-title">{book.name}</h5>
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
