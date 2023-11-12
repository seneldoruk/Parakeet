
import Navbar from "~/components/navbar";
import Posts from "~/components/post/posts";
import Login from "../components/login";


export default function Index() {
  const userLoggedIn = false
  if (userLoggedIn) {
    return (
      <div className="w-screen px-[10%] ">
        <Navbar />
        <Posts />
      </div>
    );
  }
  return <Login />
}
