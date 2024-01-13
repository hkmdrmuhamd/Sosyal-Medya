import { React } from "react";
import { Link } from "react-router-dom";
import CardContent from "@mui/material/CardContent";
import Avatar from "@mui/material/Avatar";
import { InputAdornment, OutlinedInput } from "@mui/material";

function Comment(props) {
  const { userId, userName, text } = props;

  return (
    <div>
      <CardContent>
        <OutlinedInput
          style={{ color: "black", backgroundColor: "white" }}
          disabled
          id="outlined-adornment-amount"
          multiline
          inputProps={{ maxLength: 25 }}
          fullWidth
          value={text}
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
        ></OutlinedInput>
      </CardContent>
    </div>
  );
}

export default Comment;
