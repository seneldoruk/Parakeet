import { useRef } from "react"

export default function SendPost() {
    const postText = useRef<HTMLTextAreaElement>(null)
    function sendPost() {
        console.log(postText.current)
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