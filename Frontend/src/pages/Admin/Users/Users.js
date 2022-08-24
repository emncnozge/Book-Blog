import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import Navbar from "../../../components/Navbar";
export default function Users() {
  const navigate = useNavigate();
  const [users, setUsers] = useState([]);
  const [loggedIn, setLoggedIn] = useState(false);
  const [search, setSearch] = useState("");

  useEffect(() => {
    setLoggedIn(window.localStorage.getItem("loggedIn"));
    if (!window.localStorage.getItem("loggedIn"))
      navigate("login", { replace: true });

    const axios = require("axios");
    axios
      .post("/api/user/getUsers", {
        token: "Izgg1AUtqtyzwEWxQcRIxm2rBSXPXxRv",
      })
      .then((response) => response.data)
      .then((data) => setUsers(data));
  }, [navigate]);
  function turkishToLower(e) {
    let string = e;
    let letters = { İ: "i", I: "ı", Ş: "ş", Ğ: "ğ", Ü: "ü", Ö: "ö", Ç: "ç" };
    string = string.replace(/(([İIŞĞÜÇÖ]))/g, function (letter) {
      return letters[letter];
    });
    return string.toLowerCase();
  }
  if (loggedIn)
    return (
      <>
        <Navbar />
        <div className="container-fluid">
          <input
            type="text"
            className="form-control mb-3"
            style={{ width: "min(250px, 100vw)" }}
            placeholder="Kullanıcı ara"
            onChange={(e) => setSearch(e.target.value)}
          />
          <div className="row">
            {users
              ?.filter((user) =>
                turkishToLower(user.name)
                  .trim()
                  .includes(turkishToLower(search).trim())
              )
              .map((user) => {
                return (
                  <div
                    key={user.user_id}
                    className="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2"
                  >
                    <Link
                      to={"/admin/edituser/" + user.user_id}
                      className="user"
                    >
                      <div className="card">
                        <div className="card-img-top mt-3">
                          <img
                            alt="user_photo"
                            src={"/api/user/photo/" + user.user_id}
                            className="image"
                          />
                        </div>

                        <div className="card-body">
                          <h5 className="card-title">{user.name}</h5>
                        </div>
                      </div>
                    </Link>
                  </div>
                );
              })}
          </div>
        </div>
      </>
    );
}
