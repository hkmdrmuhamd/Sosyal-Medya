import { React, useState, useEffect } from "react";
import Post from "../Post/Post";
import CssBaseline from "@mui/material/CssBaseline";
import "./Home.css";
import PostForm from "../Post/PostForm";

function Home() {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [postList, setPostList] = useState([]);

  const refreshPosts = () => {
    fetch(process.env.REACT_APP_BACKEND_URL + "/posts")
      .then((res) => res.json())
      .then(
        (result) => {
          setIsLoaded(true);
          setPostList(result);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      );
  };

  useEffect(() => {
    refreshPosts();
  }, []);

  if (error) {
    return <div>Error!!!</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return (
      <div>
        <CssBaseline />
        <div
          fixed
          style={{
            display: "flex",
            flexWrap: "wrap",
            justifyContent: "center",
            alignItems: "center",
            backgroundColor: "#f0f5ff",
          }}
        >
          <PostForm
            userId={1}
            userName={"asdasd"}
            refreshPosts={refreshPosts}
          />
          {postList.map((post, key) => (
            <Post
              key={key}
              postId={post.id}
              userId={post.userId}
              userName={post.userName}
              title={post.title}
              text={post.text}
            ></Post>
          ))}
        </div>
      </div>
    );
  }
}

export default Home;
