import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Logo from "../img/logo.png";
import { PersonCircle } from "react-bootstrap-icons";

export default function Navbar() {
  const [name, setName] = useState("");
  useEffect(() => {
    setName(window.localStorage.getItem("name"));
  }, []);

  return (
    <>
      <div className="navbar mb-4">
        <div className="row w-100">
          <div className="d-none d-sm-block col-2 col-lg-1 my-auto">
            <img src={Logo} className="img" alt="foto" />
          </div>
          <div className="col-9 col-sm-6 col-lg-4 d-flex my-auto justify-content-around">
            <Link to="/books" className="link">
              Kitaplar
            </Link>
            <Link to="/" className="link">
              Yazarlar
            </Link>
            <Link to="/" className="link">
              Arama
            </Link>
          </div>
          <div
            className="col my-auto profile"
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
            >
              <span className="d-none d-md-inline me-2">{name}</span>
              <PersonCircle size={46} color="#999"></PersonCircle>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
