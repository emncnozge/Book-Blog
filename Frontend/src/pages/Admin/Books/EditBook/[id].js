import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Select from "react-select";
import Navbar from "../../../../components/Navbar";
export default function Author() {
  const navigate = useNavigate();
  const [about, setAbout] = useState("");
  const [name, setName] = useState("");
  const [author_id, setAuthor_id] = useState(0);
  const [book_id, setBook_id] = useState(0);
  const [genre, setGenre] = useState("");
  const [loggedIn, setLoggedIn] = useState(false);
  const location = useLocation();
  const handleName = (e) => setName(e.target.value);
  const handleAbout = (e) => setAbout(e.target.value);
  const handleGenre = (e) => setGenre(e.target.value);
  const [success, setSuccess] = useState(false);
  const [error, setError] = useState(false);
  const [authors, setAuthors] = useState([]);
  const [selectedAuthor, setSelectedAuthor] = useState("");

  async function save() {
    const axios = require("axios");
    axios
      .put("/api/book/updateBook", {
        book_id: location.pathname.split("/")[3],
        name,
        about,
        author_id: selectedAuthor.value,
        genre,
      })
      .then((response) => {
        if (response.data) {
          setError(false);
          setSuccess(true);
        } else {
          setError(true);
          setSuccess(false);
        }
      });
  }

  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (
      !window.localStorage.getItem("loggedIn") &&
      !window.localStorage.getItem("isAdmin")
    )
      navigate("login", { replace: true });
    setBook_id(location.pathname.split("/")[3]);
    const axios = require("axios");
    axios
      .post("/api/book/getBook", {
        book_id: location.pathname.split("/")[3],
      })
      .then(function (response) {
        setAuthor_id(response.data.author_id);
        setName(response.data.name);
        setAbout(response.data.about);
        setGenre(response.data.genre);
        axios.get("/api/author").then((response2) => {
          let getAuthors = [];
          for (let i = 0; i < response2.data.length; i++) {
            if (response2.data[i].author_id == response.data.author_id)
              setSelectedAuthor({
                value: response2.data[i].author_id,
                label: response2.data[i].name,
              });
            getAuthors.push({
              value: response2.data[i].author_id,
              label: response2.data[i].name,
            });
          }
          setAuthors(getAuthors);
        });
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
          <div className="row">
            <div className="d-table-cell p-4 col-12 col-sm-4 col-md-3 leftBar align-items-center justify-items-center">
              <div>
                <img
                  alt="book"
                  style={{
                    maxWidth: "100%",
                    maxHeight: "100%",
                    objectFit: "cover",
                    border: "2px solid #ccc",
                    borderRadius: ".3rem",
                    padding: "0.1rem",
                    marginBottom: "1rem",
                  }}
                  src={"/api/book/photo/" + book_id}
                />
                <form
                  action="/api/book/photo"
                  name="form"
                  method="post"
                  encType="multipart/form-data"
                >
                  <div>
                    <input
                      type="text"
                      name="book_id"
                      defaultValue={book_id}
                      hidden
                    />
                    <input
                      type="file"
                      className="form-control form-control-sm"
                      name="image"
                      accept="image/png"
                    />
                  </div>
                  <input
                    type="submit"
                    className="btn btn-success upload"
                    value="Kaydet"
                  />
                </form>
              </div>
            </div>
            <div className="col-12 col-sm-7 col-md-8 rightSide">
              <div className="about mb-3">Kitap Adı</div>
              <input
                type="text"
                className="form-control form-control-lg aboutIcerik mb-3"
                value={name}
                onChange={handleName}
              />
              <div className="about">Kitap Hakkında</div>
              <textarea
                className="aboutIcerik form-control"
                value={about}
                onChange={handleAbout}
                style={{ height: "40vh" }}
              />
              <p className="my-auto label mt-2">Yazar</p>
              <Select
                name="name"
                options={authors}
                value={selectedAuthor}
                onChange={setSelectedAuthor}
              />
              <p className="my-auto label">Tür</p>
              <input
                name="genre"
                type="text"
                value={genre}
                onChange={handleGenre}
                className="form-control"
              />
              <div className="mt-2 mb-4" style={{ float: "right" }}>
                <input
                  type="button"
                  className="btn btn-success mb-1"
                  value="Kaydet"
                  onClick={save}
                />

                {success ? (
                  <div style={{ textAlign: "center", color: "green" }}>
                    Başarılı!
                  </div>
                ) : (
                  <></>
                )}
                {error ? (
                  <div style={{ textAlign: "center", color: "red" }}>
                    Başarısız!
                  </div>
                ) : (
                  <></>
                )}
              </div>
            </div>
          </div>
        </div>
      </>
    );
}
