import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
export default function MainPage() {
  const navigate = useNavigate();
  const [user_id, setUser_id] = useState("");
  const [about, setAbout] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [photo, setPhoto] = useState();
  const [loggedIn, setLoggedIn] = useState(false);

  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (!window.localStorage.getItem("loggedIn"))
      navigate("login", { replace: true });
    setUser_id(window.localStorage.getItem("user_id"));
    const axios = require("axios");
    axios
      .post("/api/user/getUser", {
        user_id: window.localStorage.getItem("user_id"),
        token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
      })
      .then(function (response) {
        setAbout(response.data.about);
        setEmail(response.data.email);
        setName(response.data.name);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);
  if (loggedIn)
    return (
      <>
        <Navbar />
        <div className="container-fluid">
          <div className="row">
            {name}
            {email}
            {about}
          </div>
        </div>
      </>
    );
}
