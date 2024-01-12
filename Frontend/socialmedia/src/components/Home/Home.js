import { React, useState, useEffect } from "react";
import axios from "axios";
import Post from "../Post/Post";
import CssBaseline from "@mui/material/CssBaseline";
import Container from "@mui/material/Container";
import "./Home.css";

function Home() {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [postList, setPostList] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const result = await axios(
          process.env.REACT_APP_BACKEND_URL + "/posts"
        );
        setIsLoaded(true);
        setPostList(result.data);
      } catch (error) {
        console.log(error);
        setIsLoaded(true);
        setError(error);
      }
    };

    fetchData();
  }, []);

  if (error) {
    return <div>Error!!!</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return (
      <div>
        <CssBaseline />
        <Container
          fixed
          style={{
            display: "flex",
            flexWrap: "wrap",
            justifyContent: "center",
            alignItems: "center",
            backgroundColor: "#cfe8fc",
            height: "100vh",
          }}
        >
          {postList.map((post, key) => (
            <Post
              key={post.id}
              userId={post.userId}
              userName={post.userName}
              title={post.title}
              text={post.text}
            ></Post>
          ))}
        </Container>
      </div>
    );
  }
}

export default Home;
