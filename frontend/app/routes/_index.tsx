import { redirect, type MetaFunction } from "@remix-run/node";
import Navbar from "~/components/navbar";
import Posts from "~/components/post/posts";


export const loader = () => {
  const userLoggedIn = true
  if (!userLoggedIn) {
    return redirect('login')
  }
  return null
}
export default function Index() {
  return (
    <div className="w-screen px-[10%]">
      <Navbar />
      <Posts />
    </div>
  );
}
