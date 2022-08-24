import axios from "axios";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Select from "react-select";
import Navbar from "../../../components/Navbar";
export default function Profile() {
  const navigate = useNavigate();
  const [about, setAbout] = useState("");
  const [name, setName] = useState("");
  const [genre, setGenre] = useState("");
  const [loggedIn, setLoggedIn] = useState(false);
  const [authors, setAuthors] = useState([]);
  const [selectedAuthor, setSelectedAuthor] = useState("");

  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (
      !window.localStorage.getItem("loggedIn") &&
      !window.localStorage.getItem("isAdmin")
    )
      navigate("login", { replace: true });

    axios.get("/api/author").then((response) => {
      let getAuthors = [];
      for (let i = 0; i < response.data.length; i++) {
        getAuthors.push({
          value: response.data[i].author_id,
          label: response.data[i].name,
        });
      }
      setAuthors(getAuthors);
    });
  }, [navigate]);

  const handleName = (e) => setName(e.target.value);
  const handleAbout = (e) => setAbout(e.target.value);
  const handleGenre = (e) => setGenre(e.target.value);

  async function save() {
    console.log(selectedAuthor.value);
    axios
      .post("/api/book", {
        name,
        about,
        author_id: selectedAuthor.value,
        photo_url: "",
        genre,
      })
      .then((response) => navigate("../admin/editbook/" + response.data));
  }
  if (loggedIn && window.localStorage.getItem("isAdmin"))
    return (
      <>
        <Navbar />
        <div className="container-fluid">
          <div className="row ">
            <div className="col">
              <div className="underPhoto">Kitap Bilgileri</div>

              <p className="my-auto label">Kitap Adı</p>
              <input
                name="name"
                type="text"
                value={name}
                onChange={handleName}
                className="form-control"
              />
              <p className="my-auto label">Tür</p>
              <input
                name="genre"
                type="text"
                value={genre}
                onChange={handleGenre}
                className="form-control"
              />
              <p className="my-auto label mt-2">Hakkında</p>
              <textarea
                name="about"
                value={about}
                onChange={handleAbout}
                className="form-control"
              />
              <p className="my-auto label mt-2">Yazar</p>
              <Select
                name="name"
                options={authors}
                value={selectedAuthor}
                onChange={setSelectedAuthor}
              />

              <div className="mt-2 mb-4">
                <input
                  type="button"
                  className="btn btn-success"
                  value="Kaydet"
                  onClick={save}
                />
              </div>
            </div>
          </div>
        </div>
      </>
    );
}
