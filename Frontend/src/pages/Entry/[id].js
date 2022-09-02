import React, { useState, useEffect } from "react";
import { useNavigate, useLocation, Link } from "react-router-dom";
import { PenFill, XLg } from "react-bootstrap-icons";
import Navbar from "../../components/Navbar";
export default function Entry() {
  const navigate = useNavigate();
  const [user_id, setUser_id] = useState(0);
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [header, setHeader] = useState("");
  const [entry, setEntry] = useState("");
  const [entry_user_id, setEntry_user_id] = useState(0);
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
        setHeader(response.data.header);
        setEntry(response.data.entry);
        setEntry_user_id(response.data.userid);
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
  const deleteEntry = () => {
    const axios = require("axios");
    axios
      .post("/api/entry/delete", {
        entry_id: location.pathname.split("/")[4],
      })
      .then(navigate(-1));
  };
  const editEntry = () =>
    navigate(
      "/book/" +
        location.pathname.split("/")[2] +
        "/editentry/" +
        location.pathname.split("/")[4]
    );
  if (loggedIn)
    return (
      <>
        <Navbar />
        <div className="container-fluid">
          <p className="baslik">{name}</p>
          {entry_user_id == window.localStorage.getItem("user_id") ? (
            <div style={{ textAlign: "right" }}>
              <button onClick={editEntry} className="btn edit">
                <PenFill size={28} style={{ marginBottom: "5px" }}></PenFill>
              </button>
              <button onClick={deleteEntry} className="btn delete ms-4">
                <XLg
                  size={28}
                  color={"red"}
                  style={{ marginBottom: "5px" }}
                ></XLg>
              </button>
            </div>
          ) : (
            <></>
          )}

          <div className="row">
            <div className="d-table-cell p-4 col-12 col-sm-4 col-md-3 leftBar align-items-center justify-items-center">
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
            <div className="col-12 col-sm-7 col-md-8 rightSide">
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
