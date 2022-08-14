import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Navbar from "../components/Navbar";
export default function MainPage() {
  const [books, setBooks] = useState([]);
  useEffect(() => {
    const axios = require("axios");

    axios
      .get("http://localhost:8080/api/book/last20")
      .then((response) => response.data)
      .then((data) => setBooks(data));
  }, []);
  return (
    <>
      <Navbar />
      <div className="container-fluid">
        <div className="row">
          <form
            action="http://localhost:8080/api/book/photo"
            object="{user}"
            method="post"
            encType="multipart/form-data"
          >
            <div>
              <label>Photos: </label>
              <input type="file" name="image" accept="image/png, image/jpeg" />
            </div>
            <input type="submit" value="Upload" />
          </form>
          {books?.map((book) => {
            return (
              <div
                key={book.book_id}
                className="col-12 col-sm-6 col-md-4 col-lg-3"
              >
                <Link to={book.book_id} className="book">
                  <div className="card">
                    <div className="card-img-top mt-3">
                      <img
                        alt="book_photo"
                        src={
                          "http://localhost:8080/api/book/photo/" + book.book_id
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
