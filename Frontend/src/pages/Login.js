// eslint-disable-next-line
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Logo from "../img/logo.png";
import Modal from "react-bootstrap/Modal";
export default function Navbar() {
  const axios = require("axios");
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [registerEmail, setRegisterEmail] = useState("");
  const [registerPassword, setRegisterPassword] = useState("");
  const [registerName, setRegisterName] = useState("");
  const [error, setError] = useState(false);
  const [backendError, setBackendError] = useState(false);
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const handleEmail = (e) => setEmail(e.target.value);
  const handlePassword = (e) => setPassword(e.target.value);
  const handleRegisterEmail = (e) => setRegisterEmail(e.target.value);
  const handleRegisterPassword = (e) => setRegisterPassword(e.target.value);
  const handleRegisterName = (e) => setRegisterName(e.target.value);
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
        .then(async (response) => {
          if (typeof response.data !== String && response.data !== 0) {
            await axios
              .post("/api/user/getUser", {
                user_id: response.data,
                token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
              })
              .then(function (response) {
                window.localStorage.setItem("user_id", response.data.user_id);
                window.localStorage.setItem("name", response.data.name);
              })
              .catch(function (error) {
                console.log(error);
              });
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

  const handleRegister = async (e) => {
    e.preventDefault();
    if (
      /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(registerEmail) &&
      registerPassword.toLowerCase !== registerPassword &&
      registerPassword.toUpperCase !== registerPassword &&
      registerPassword.length >= 8 &&
      /\d/.test(registerPassword)
    ) {
      setError(false);
    } else {
      setError(true);
    }
    if (!error) {
      await axios
        .post("/api/user/addUser", {
          email: registerEmail,
          password: registerPassword,
          name: registerName,
          token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
        })
        .then(async (response) => {
          if (typeof response.data !== String && response.data !== 0) {
            await axios
              .post("/api/user/getUser", {
                user_id: response.data,
                token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
              })
              .then(function (response) {
                window.localStorage.setItem("user_id", response.data.user_id);
                window.localStorage.setItem("name", response.data.name);
              })
              .catch(function (error) {
                console.log(error);
              });
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
                <p onClick={handleShow} className="register">
                  Kayıt Ol
                </p>
              </div>

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
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Kayıt ol</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <input
            name="email"
            type="email"
            className="form-control form-control-lg mt-2 mb-3"
            placeholder="E-posta"
            value={registerEmail}
            onChange={handleRegisterEmail}
            required
          />

          <input
            name="email"
            type="text"
            className="form-control form-control-lg mt-2 mb-3"
            placeholder="Ad Soyad"
            value={registerName}
            onChange={handleRegisterName}
            required
          />
          <input
            name="email"
            type="password"
            className="form-control form-control-lg mt-2 mb-3"
            placeholder="Şifre"
            value={registerPassword}
            onChange={handleRegisterPassword}
            required
          />
        </Modal.Body>
        <Modal.Footer>
          <input
            type="button"
            className="btn btn-secondary"
            onClick={handleClose}
            value="Close"
          ></input>
          <input
            type="button"
            className="btn btn-primary"
            onClick={handleRegister}
            value="Kayıt ol"
          ></input>
        </Modal.Footer>
      </Modal>
    </>
  );
}
