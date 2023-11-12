import { Link } from "@remix-run/react"

export interface Props {
    text: string,
    user: string,
}
export default function Post({ text, user }: Props) {
    function onClick() {
        console.log(text)
    }
    return (
        <div className="card  bg-base-100 shadow-xl w-full my-16 cursor-pointer " onClick={onClick}>
            <div className="card-body min-h-16">
                <Link to={`/user/${user}`}>
                    <h2 className="card-title hover:underline">
                        {`@${user}`}
                    </h2>
                </Link>

                <p className="cursor-text">{text}</p>
            </div>
        </div>

    )
}