import axios from "axios";
import React, { useState, useEffect } from "react";
import { useNavigate, useLocation, Link } from "react-router-dom";
import Navbar from "../../components/Navbar";
export default function EditEntry() {
  const navigate = useNavigate();
  const [entry, setEntry] = useState("");
  const [header, setHeader] = useState("");
  const [name, setName] = useState("");
  const [book_id, setBook_id] = useState(0);
  const [loggedIn, setLoggedIn] = useState(false);
  const location = useLocation();

  const handleHeader = (e) => setHeader(e.target.value);
  const handleEntry = (e) => setEntry(e.target.value);

  const save = () => {
    axios
      .put("/api/entry", {
        entry_id: location.pathname.split("/")[4],
        header: header,
        entry: entry,
      })
      .then(
        navigate(
          "/book/" +
            location.pathname.split("/")[2] +
            "/entry/" +
            location.pathname.split("/")[4]
        )
      );
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
      })
      .catch(function (error) {
        console.log(error);
      });
    axios
      .get("/api/entry/getEntry?entry_id=" + location.pathname.split("/")[4])
      .then((response) => {
        setHeader(response.data.header);
        setEntry(response.data.entry);
      });
  }, [navigate, location.pathname]);

  if (loggedIn)
    return (
      <>
        <Navbar />
        <div className="container-fluid">
          <p className="baslik">{name}</p>

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
              <label htmlFor="baslik" className="label">
                Başlık:
              </label>
              <input
                id="baslik"
                className="form-control form-control-lg mb-4"
                placeholder="Başlık giriniz"
                value={header}
                onChange={handleHeader}
              />
              <label htmlFor="icerik" className="label">
                Blog içeriği:
              </label>
              <textarea
                id="icerik"
                className="form-control icerik"
                placeholder="Blog içeriğini giriniz"
                value={entry}
                onChange={handleEntry}
              />
              <div className="mt-4">
                <button
                  style={{ float: "right", borderRadius: "1rem" }}
                  className="btn btn-success"
                  onClick={save}
                >
                  Kaydet
                </button>
                <Link
                  to={"/book/" + location.pathname.split("/")[2]}
                  style={{
                    float: "right",
                    marginRight: "1rem",
                    borderRadius: "1rem",
                  }}
                  className="btn btn-secondary"
                >
                  İptal
                </Link>
              </div>
            </div>
          </div>
        </div>
      </>
    );
}
