import React, { useState, useEffect } from "react";
import { useNavigate, useLocation, Link } from "react-router-dom";
import Navbar from "../../components/Navbar";
export default function Entry() {
  const navigate = useNavigate();
  const [user_id, setUser_id] = useState(0);
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [header, setHeader] = useState("");
  const [entry, setEntry] = useState("");
  const [book_id, setBook_id] = useState(0);
  const [loggedIn, setLoggedIn] = useState(false);
  const location = useLocation();

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
        console.log(response.data);
        setHeader(response.data.header);
        setEntry(response.data.entry);
        axios
          .post("/api/user/getUser", {
            user_id: response.data.userid,
            token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
          })
          .then((response2) => {
            setUsername(response2.data.name);
            setUser_id(response.data.userid);
          });
      });
  }, [navigate, location.pathname]);

  if (loggedIn)
    return (
      <>
        <Navbar />
        <div className="container-fluid">
          <p className="baslik">{name}</p>

          <div className="row">
            <div className="d-table-cell my-auto p-4 col-12 col-sm-4 col-md-3 leftBar align-items-center justify-items-center">
              <div style={{ textAlign: "center" }}>
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
            <div className="col-12 col-sm-7 col-md-8">
              <div className="about">{header}</div>
              <p className="aboutIcerik">{entry}</p>
              <Link to={"/user/" + user_id} className="entryAuthor">
                - {username}
              </Link>
            </div>
          </div>
        </div>
      </>
    );
}
