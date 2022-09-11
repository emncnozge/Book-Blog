import React from "react";
import { Link } from "react-router-dom";
export default function Card(props) {
  if (props.type === "book")
    return (
      <div
        key={props.data.book_id}
        className="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2"
      >
        <Link to={"/book/" + props.data.book_id} className="book">
          <div className="card">
            <div className="img-top">
              <img
                alt="book_photo"
                src={"/api/book/photo/" + props.data.book_id}
                className="image"
              />
            </div>

            <div className="card-body">
              <h5 className="card-title">{props.data.name}</h5>
            </div>
          </div>
        </Link>
      </div>
    );
  else if (props.type === "author")
    return (
      <div
        key={props.data.author_id}
        className="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2"
      >
        <Link to={"/author/" + props.data.author_id} className="author">
          <div className="card">
            <div className="img-top">
              <img
                alt="author_photo"
                src={"/api/author/photo/" + props.data.author_id}
                className="image"
              />
            </div>
            <div className="card-body">
              <h5 className="card-title">{props.data.name}</h5>
            </div>
          </div>
        </Link>
      </div>
    );
  else if (props.type === "user")
    return (
      <div
        key={props.data.user_id}
        className="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2"
      >
        <Link to={"/user/" + props.data.user_id} className="user">
          <div className="card">
            <div className="img-top">
              <img
                alt="user_photo"
                src={"/api/user/photo/" + props.data.user_id}
                className="image"
              />
            </div>
            <div className="card-body">
              <h5 className="card-title">{props.data.name}</h5>
            </div>
          </div>
        </Link>
      </div>
    );
}
