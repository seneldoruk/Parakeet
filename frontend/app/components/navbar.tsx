import { Link } from "@remix-run/react";
import { FormEvent, useRef } from "react";
import { useAppDispatch } from "app/state/hooks"
import { mockPosts, setPosts } from "../state/postsSlice";

export default function Navbar() {
    const searchRef = useRef<HTMLInputElement>(null)
    const dispatch = useAppDispatch();

    function searchUser(e: FormEvent) {
        e.preventDefault()
        console.log(searchRef.current?.value)
    }
    function showMyPosts() {
        dispatch(setPosts({ posts: [], viewing: "self" }))
    }
    function allPosts() {
        dispatch(setPosts({ posts: mockPosts, viewing: "all" }))
    }
    return (
        <div className="navbar bg-base-100 border my-1 ">
            <div className="flex-1">
                <button onClick={allPosts} className="btn btn-ghost normal-case text-xl">Parakeet</button>
            </div>
            <form className="form-control flex-none" onSubmit={searchUser}>
                <input type="text" placeholder="Search User" className="input input-bordered w-24 md:w-auto input-sm" ref={searchRef} />
            </form>
            <div className="flex-none">
                <ul className="menu menu-horizontal px-1" >
                    <li><b onClick={showMyPosts}>Show My Posts</b></li>
                </ul>
            </div>
        </div>
    )
}