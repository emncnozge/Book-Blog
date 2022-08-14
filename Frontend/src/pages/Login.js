// eslint-disable-next-line
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Logo from "../img/logo.png";

export default function Navbar() {
  const axios = require("axios");
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(false);
  const [backendError, setBackendError] = useState(false);
  const handleEmail = (e) => setEmail(e.target.value);
  const handlePassword = (e) => setPassword(e.target.value);
  const handleLogin = async (e) => {
    e.preventDefault();
    if (
      /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(email) &&
      password.toLowerCase !== password &&
      password.toUpperCase !== password &&
      password.length >= 8 &&
      /\d/.test(password)
    ) {
      setError(false);
    } else {
      setError(true);
    }
    if (!error) {
      await axios
        .post("/api/user/login", {
          email: email,
          password: password,
          token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
        })
        .then(function (response) {
          if (response.data) {
            navigate("/");
          } else {
            setBackendError(true);
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    }
  };
  return (
    <>
      <div className="login">
        <div className="h-100 d-flex justify-content-center align-items-center">
          <div className="loginCard">
            <div style={{ textAlign: "center" }}>
              <img
                alt="logo"
                src={Logo}
                className="logo"
                style={{ width: "120px" }}
              ></img>
            </div>
            <form onSubmit={handleLogin}>
              <input
                name="email"
                type="email"
                className="form-control form-control-lg mt-2 mb-3"
                placeholder="E-posta"
                value={email}
                onChange={handleEmail}
                required
              />

              <input
                name="password"
                type="password"
                className="form-control form-control-lg mb-3"
                placeholder="Şifre"
                value={password}
                onChange={handlePassword}
                required
              />

              <input
                name="submit"
                type="submit"
                className="btn btn-primary mb-3 w-100"
                value="Giriş Yap"
                onSubmit={handleLogin}
              />
              <div style={{ textAlign: "center" }}>
                <Link to="/register" className="register">
                  Kayıt Ol
                </Link>
              </div>
              {error ? (
                <div className="error mt-2">
                  Kullanıcı adı veya şifre formatı hatalı!
                </div>
              ) : (
                <></>
              )}
              {backendError ? (
                <div className="error mt-2">
                  Kullanıcı adı veya şifre hatalı!
                </div>
              ) : (
                <></>
              )}
            </form>
          </div>
        </div>
      </div>
    </>
  );
}
