import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Logo from "../img/logo.png";
import $ from "jquery";

export default function Navbar() {
  const [name, setName] = useState("");
  const [user_id, setUser_id] = useState("");
  useEffect(() => {
    setName(window.localStorage.getItem("name"));
    setUser_id(window.localStorage.getItem("user_id"));
  }, []);

  const handleLogout = () => {
    window.localStorage.removeItem("user_id");
    window.localStorage.removeItem("name");
    window.localStorage.removeItem("loggedIn");
  };
  return (
    <>
      <div className="navbar mb-4">
        <div className="row w-100">
          <div className="d-none d-sm-block col-2 col-lg-1 my-auto">
            <Link to="/">
              <img src={Logo} className="img" alt="foto" />
            </Link>
          </div>
          <div className="col-9 col-sm-6 col-lg-4 d-flex my-auto justify-content-around">
            <Link to="/books" className="link">
              Kitaplar
            </Link>
            <Link to="/authors" className="link">
              Yazarlar
            </Link>
            <Link to="/" className="link">
              Arama
            </Link>
          </div>
          <div
            className="col my-auto dropdown profile"
            style={{ float: "right", textAlign: "right" }}
          >
            <div
              style={{
                border: "2px solid #999",
                borderRadius: "3rem",
                textAlign: "left",
                padding: ".4rem",
                width: "fit-content",
                float: "right",
              }}
              className="dropbtn"
              onClick={(e) => $(".dropdowncontent").slideToggle()}
            >
              <span className="d-none d-md-inline me-2">{name}</span>
              <img
                style={{
                  width: "50px",
                  height: "50px",
                  borderRadius: "50%",
                  objectFit: "cover",
                }}
                src={"http://localhost:8080/api/user/photo/" + user_id}
                color="#999"
              ></img>
              <div className="dropdowncontent">
                <Link to="/profile">Profil</Link>
                <a href="#">Favori Kitaplar</a>
                <a href="#">Favori Yazarlar</a>
                <a href="#">Favori Kullanıcılar</a>
                <Link to="/login" onClick={handleLogout}>
                  Çıkış Yap
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
