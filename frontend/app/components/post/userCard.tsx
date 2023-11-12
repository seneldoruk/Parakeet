import { useAppSelector } from "~/state/hooks"

export default function UserCard() {
    const username = useAppSelector(state => state.posts.viewing)
    const isFollowed = useAppSelector(state => state.user.following.includes(username))

    function stopFollowing() {

    }
    function follow() {

    }

    return (
        <div className="w-full flex justify-center">
            <div className="card  bg-base-100 shadow-xl w-[30%] my-16 cursor-pointer" >
                <div className="card-body min-h-16 flex flex-row justify-center items-center w-full">
                    <h2 className="text-xl font-bold mr-3">@{username}</h2>
                    {isFollowed ?
                        <button className="btn btn-sm btn-error ml-3" onClick={stopFollowing}>Stop Following</button>
                        :
                        <button className="btn btn-sm btn-primary ml-3" onClick={follow}>Follow</button>
                    }
                </div>
            </div>
        </div>
    )
}