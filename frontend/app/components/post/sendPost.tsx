import { useRef } from "react"
import { useAppDispatch, useAppSelector } from "~/state/hooks"
import { addPosts } from "~/state/postsSlice"

export default function SendPost() {
    const postText = useRef<HTMLTextAreaElement>(null)
    const dispatch = useAppDispatch()
    const username = useAppSelector(state => state.user.username)
    function sendPost() {
        console.log(postText.current?.value)
        dispatch(addPosts({ posts: [{ text: postText.current?.value!, user: username! }], addToStart: true }))
    }
    return (
        <>
            <div className="card  bg-base-100 shadow-xl w-full my-16" >
                <div className="card-body flex-row min-h-16">
                    <textarea className="textarea textarea-bordered w-full resize-y" placeholder="Write your post" ref={postText}></textarea>

                    <button className="btn btn-primary h-auto" onClick={sendPost}>Send</button>

                </div>
            </div>
        </>
    )
}