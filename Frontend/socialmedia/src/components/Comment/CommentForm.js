import { React, useState } from "react";
import { Link } from "react-router-dom";
import CardContent from "@mui/material/CardContent";
import Avatar from "@mui/material/Avatar";
import { Button, InputAdornment, OutlinedInput } from "@mui/material";

function CommentForm(props) {
  const { userId, userName, postId } = props;
  const [text, setText] = useState("");

  const saveComment = () => {
    fetch(process.env.REACT_APP_BACKEND_URL + "/comments", {
      method: "POST",
      body: JSON.stringify({
        postId: postId,
        userId: userId,
        text: text,
      }),
      headers: {
        "Content-type": "application/json",
      },
    })
      .then((res) => res.json())
      .catch((err) => console.log(err));
  };

  const handleSubmit = () => {
    saveComment();
    setText("");
  };

  const handleChange = (value) => {
    setText(value);
  };

  return (
    <div>
      <CardContent>
        <OutlinedInput
          style={{ color: "black", backgroundColor: "white" }}
          id="outlined-adornment-amount"
          multiline
          inputProps={{ maxLength: 250 }}
          fullWidth
          onChange={(i) => handleChange(i.target.value)}
          startAdornment={
            <InputAdornment position="start">
              <Link
                className="link"
                to={{ pathname: "/users/" + userId }}
                style={{
                  textDecoration: "none",
                  boxShadow: "none",
                  color: "black",
                }}
              >
                <Avatar
                  aria-label="recipe"
                  sx={{ width: "16x", height: "32px" }}
                >
                  {userName.charAt(0).toUpperCase()}
                </Avatar>
              </Link>
            </InputAdornment>
          }
          endAdornment={
            <InputAdornment position="end">
              <Button
                variant="contained"
                style={{
                  background:
                    "linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)",
                  color: "white",
                }}
                onClick={handleSubmit}
              >
                Comment
              </Button>
            </InputAdornment>
          }
          value={text}
        ></OutlinedInput>
      </CardContent>
    </div>
  );
}

export default CommentForm;
