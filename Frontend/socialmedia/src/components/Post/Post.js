import { React, useState, useRef, useEffect } from "react";
import { Link } from "react-router-dom";
import { styled } from "@mui/material/styles";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import Collapse from "@mui/material/Collapse";
import Avatar from "@mui/material/Avatar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import FavoriteIcon from "@mui/icons-material/Favorite";
import CommentIcon from "@mui/icons-material/Comment";
import { Container } from "@mui/material";
import Comment from "../Comment/Comment.js";
import CommentForm from "../Comment/CommentForm.js";

const ExpandMore = styled((props) => {
  const { expand, ...other } = props;
  return <IconButton {...other} />;
})(({ theme, expand }) => ({
  transform: !expand ? "rotate(0deg)" : "rotate(180deg)",
  marginLeft: "auto",
  transition: theme.transitions.create("transform", {
    duration: theme.transitions.duration.shortest,
  }),
}));

function Post(props) {
  const { userId, userName, title, text, postId } = props;
  const [expanded, setExpanded] = useState(false);
  const [likeColor, setLikeColor] = useState(false);
  const [commentList, setCommentList] = useState([]);
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const isInitialMount = useRef(true);

  const handleExpandClick = () => {
    setExpanded(!expanded);
    refreshComments();
  };

  const handleChangeLikeColor = () => {
    setLikeColor(!likeColor);
  };

  const refreshComments = () => {
    fetch(process.env.REACT_APP_BACKEND_URL + "/comments?postId=" + postId)
      .then((res) => res.json())
      .then(
        (result) => {
          setIsLoaded(true);
          setCommentList(result);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      );
  };

  useEffect(() => {
    if (isInitialMount.current) {
      isInitialMount.current = false;
    } else {
      refreshComments();
    }
  }, []);

  return (
    <div className="postContainer">
      <Card sx={{ width: 800, textAlign: "left", margin: "20px" }}>
        <CardHeader
          avatar={
            <Link className="link" to={{ pathname: "/users/" + userId }}>
              <Avatar
                sx={{
                  background:
                    "linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)",
                }}
                aria-label="recipe"
              >
                {userName.charAt(0).toUpperCase()}
                {/* userName içerisindeki ilk harfi al bunu büyük harf yap */}
              </Avatar>
            </Link>
          }
          title={title}
        />
        <CardContent>
          <Typography variant="body2" color="text.secondary">
            {text}
          </Typography>
        </CardContent>
        <CardActions disableSpacing>
          <IconButton
            onClick={handleChangeLikeColor}
            aria-label="add to favorites"
          >
            <FavoriteIcon style={likeColor ? { color: "red" } : null} />
          </IconButton>
          <ExpandMore
            expand={expanded}
            onClick={handleExpandClick}
            aria-expanded={expanded}
            aria-label="show more"
          >
            <CommentIcon />
          </ExpandMore>
        </CardActions>
        <Collapse in={expanded} timeout="auto" unmountOnExit>
          <Container fixed>
            {error
              ? "error"
              : isLoaded
              ? commentList.map((comment, key) => (
                  <Comment
                    key={key}
                    userId={1}
                    userName={"USER"}
                    text={comment.text}
                  ></Comment>
                ))
              : "Loading"}
            <CommentForm userId={1} userName={"USER"} postId={postId} />
          </Container>
        </Collapse>
      </Card>
    </div>
  );
}

export default Post;
