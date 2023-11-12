import { Link } from "@remix-run/react";

export default function Navbar() {
    return (
        <div className="navbar bg-base-100 border my-1 ">
            <div className="flex-1">
                <Link to="/" className="btn btn-ghost normal-case text-xl">Parakeet</Link>
            </div>
            <div className="flex-none">
                <ul className="menu menu-horizontal px-1">
                    <li><a>Me</a></li>
                </ul>
            </div>
        </div>
    )
}