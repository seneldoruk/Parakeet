import { useRef } from "react";
import Post, { Props as PostProps } from "~/components/post/post";
import SendPost from "./sendPost";
import UserCard from "./userCard";
import { useAppSelector } from "app/state/hooks";

export default function Posts() {
    const posts: PostProps[] = useAppSelector((state) => state.posts.posts)
    const viewingAnotherUser = !useAppSelector(state => ["all", "self"].includes(state.posts.viewing))

    const postText = useRef<HTMLTextAreaElement>(null)
    function sendPost() {
        console.log(postText.current?.value)
    }
    return (
        <>
            {viewingAnotherUser ? <UserCard /> : <SendPost />}
            {posts.map(({ user, text }) => <Post text={text} user={user} />)}

            <button className="btn btn-primary w-full mb-12">Load More Posts</button>
        </>
    )
}