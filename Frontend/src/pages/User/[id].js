import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import Navbar from "../../components/Navbar";
export default function Book() {
  const navigate = useNavigate();
  const [about, setAbout] = useState("");
  const [name, setName] = useState("");
  const [user_id, setUser_id] = useState(0);
  const [loggedIn, setLoggedIn] = useState(false);
  const location = useLocation();

  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (!window.localStorage.getItem("loggedIn"))
      navigate("login", { replace: true });
    setUser_id(location.pathname.split("/")[2]);
    const axios = require("axios");
    axios
      .post("/api/user/getUser", {
        user_id: location.pathname.split("/")[2],
        token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
      })
      .then(function (response) {
        setUser_id(response.data.setUser_id);
        setName(response.data.name);
        setAbout(response.data.about);
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
          <div className="row ">
            <p className="baslik">{name}</p>
            <div className="d-table-cell my-auto p-4 col-12 col-sm-4 col-md-3 leftBar align-items-center justify-items-center">
              <div>
                <img
                  alt="author"
                  style={{
                    maxWidth: "80%",
                    maxHeight: "80%",
                    objectFit: "cover",
                  }}
                  src={"/api/user/photo/" + location.pathname.split("/")[2]}
                />
              </div>
            </div>
            <div className="col-12 col-sm-7 col-md-8">
              <div className="about">Kullanıcı Hakkında</div>
              <p className="aboutIcerik">{about}</p>
            </div>
          </div>
        </div>
      </>
    );
}
