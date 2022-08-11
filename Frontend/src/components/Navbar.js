import React from "react";
import { Link } from "react-router-dom";
import Logo from "../logo.svg";
import { PersonCircle } from "react-bootstrap-icons";
export default function Navbar() {
  return (
    <>
      <div className="navbar">
        <div className="row w-100">
          <div className="d-none d-sm-block col-2 col-lg-1 my-auto">
            <img src={Logo} className="img" alt="foto" />
          </div>
          <div className="col-9 col-sm-7 col-md-8 col-lg-4 d-flex my-auto justify-content-around">
            <Link to="/" className="link">
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
              <span className="d-none d-lg-inline me-2">Emin Can ÖZGE</span>
              <PersonCircle size={50} color="#999"></PersonCircle>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
