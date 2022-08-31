import React, { useEffect, useState, useRef } from "react";
import { Link } from "react-router-dom";
import Logo from "../img/logo.png";
import $ from "jquery";
import Select from "react-select";
import Overlay from "react-bootstrap/Overlay";

export default function Navbar() {
  const [name, setName] = useState("");
  const [user_id, setUser_id] = useState("");
  const [show, setShow] = useState(false);
  const target = useRef(null);
  const [genres, setGenres] = useState([]);
  const [selectedGenre, setSelectedGenre] = useState("");
  const [books, setBooks] = useState([]);
  const [selectedBook, setSelectedBook] = useState("");
  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState("");
  useEffect(() => {
    setName(window.localStorage.getItem("name"));
    setUser_id(window.localStorage.getItem("user_id"));

    const axios = require("axios");
    axios
      .get("/api/book/getGenres")
      .then((response) => {
        let getGenres = [];
        for (let i = 0; i < response.data.length; i++) {
          getGenres.push({ value: response.data[i], label: response.data[i] });
        }
        setGenres(getGenres);
      })
      .catch(function (error) {
        console.log(error);
      });
    axios
      .post("/api/user/getUsers", { token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv" })
      .then((response) => {
        let getUsers = [];
        for (let i = 0; i < response.data.length; i++) {
          if (
            response.data[i].user_id != window.localStorage.getItem("user_id")
          )
            getUsers.push({
              value: response.data[i].user_id,
              label: response.data[i].name,
            });
        }
        setUsers(getUsers);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);

  useEffect(() => {
    setSelectedBook("");
    setBooks([]);
    const axios = require("axios");
    axios
      .post("/api/book/getBooksByGenre", { genre: selectedGenre.value })
      .then((response) => {
        let getOption = [];
        for (let i = 0; i < response.data.length; i++) {
          getOption.push({
            value: response.data[i].book_id,
            label: response.data[i].name,
          });
        }
        setBooks(getOption);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [selectedGenre]);

  const handleLogout = () => {
    window.localStorage.removeItem("user_id");
    window.localStorage.removeItem("name");
    window.localStorage.removeItem("loggedIn");
    window.localStorage.removeItem("is_admin");
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
            <span className="link" ref={target} onClick={() => setShow(!show)}>
              Arama
            </span>
            <Overlay target={target.current} show={show} placement="bottom">
              {({ placement, arrowProps, show: _show, popper, ...props }) => (
                <div
                  {...props}
                  style={{
                    position: "absolute",
                    backgroundColor: "#fff",
                    border: "1px solid #eee",
                    padding: "1rem",
                    color: "black",
                    borderRadius: ".5rem",
                    zIndex: 999999,
                    width: "min(30vw, 300px)",
                    ...props.style,
                  }}
                >
                  <Select
                    options={users}
                    value={selectedUser}
                    placeholder={
                      users?.length > 0 ? "Kullanıcı" : "Kullanıcı bulunamadı"
                    }
                    isDisabled={users?.length > 0 ? false : true}
                    onChange={setSelectedUser}
                  />
                  <div className="search">
                    {users?.length > 0 ? (
                      <Link
                        to={"/user/" + selectedUser.value}
                        className="btn btn-secondary mt-2"
                      >
                        Git
                      </Link>
                    ) : (
                      <></>
                    )}
                  </div>
                  <hr />
                  <Select
                    options={genres}
                    value={selectedGenre}
                    className="mb-2"
                    placeholder={genres?.length > 0 ? "Tür" : "Tür bulunamadı"}
                    isDisabled={genres?.length > 0 ? false : true}
                    onChange={setSelectedGenre}
                  />

                  <Select
                    options={books}
                    className="mb-2"
                    placeholder="Kitap"
                    value={selectedBook}
                    isDisabled={selectedGenre?.value?.length > 0 ? false : true}
                    onChange={setSelectedBook}
                  />
                  <div className="search">
                    {genres?.length > 0 ? (
                      <Link
                        to={"/book/" + selectedBook.value}
                        className="btn btn-secondary"
                      >
                        Git
                      </Link>
                    ) : (
                      <></>
                    )}
                  </div>
                </div>
              )}
            </Overlay>
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
                alt="user_photo"
                src={"/api/user/photo/" + user_id}
                color="#999"
              ></img>
              <div className="dropdowncontent">
                {localStorage.getItem("isAdmin") === true ? (
                  <>
                    <div
                      style={{
                        cursor: "auto",
                        textAlign: "center",
                        marginTop: ".4rem",
                      }}
                    >
                      Admin
                      <hr style={{ margin: 0, marginTop: "5px" }} />
                    </div>
                    <Link to="/admin/users">Kullanıcılar</Link>
                    <Link to="/admin/books">Kitaplar</Link>
                    <Link to="/admin/authors">Yazarlar</Link>
                  </>
                ) : (
                  <></>
                )}
                <Link to="/profile">Profil</Link>
                <Link to="/favoritebooks">Favori Kitaplar</Link>
                <Link to="/favoriteauthors">Favori Yazarlar</Link>
                <Link to="/favoriteusers">Favori Kullanıcılar</Link>

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
