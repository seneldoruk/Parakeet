import { useRef } from "react";
import Post, { Props as PostProps } from "~/components/post/post";
import SendPost from "./sendPost";
import UserCard from "./userCard";
import { useAppDispatch, useAppSelector } from "app/state/hooks";
import { addPosts } from "~/state/postsSlice";

export default function Posts() {
    const dispatch = useAppDispatch()
    const posts: PostProps[] = useAppSelector((state) => state.posts.posts)
    const viewingAnotherUser = !useAppSelector(state => ["all", "self"].includes(state.posts.viewing))

    const postText = useRef<HTMLTextAreaElement>(null)
    function sendPost() {
        console.log(postText.current?.value)
    }
    function loadMorePosts() {
        dispatch(addPosts({ posts: [{ text: "asdasdasd", user: "asdgasdg" }], addToStart: false }))

    }
    return (
        <>
            {viewingAnotherUser ? <UserCard /> : <SendPost />}
            {posts.map(({ user, text }) => <Post text={text} user={user} />)}

            <button className="btn btn-primary w-full mb-12" onClick={loadMorePosts}>Load More Posts</button>
        </>
    )
}