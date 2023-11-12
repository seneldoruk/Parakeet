import { useRef } from "react";
import Post, { Props as PostProps } from "~/components/post/post";
import SendPost from "./sendPost";
const posts: PostProps[] = [
    {
        user: "alice",
        text: " Suspendisse et malesuada odio, eu rhoncus odio. Donec commodo tellus nec risus tempus feugiat. Sed eu libero et urna fermentum faucibus. Nulla facilisi. Nulla facilisi. Fusce id est a quam vestibulum euismod in id risus."
    },
    {
        user: "bob",
        text: "In hac habitasse platea dictumst. Proin hendrerit justo id augue egestas tincidunt. Sed in tellus nec dolor vulputate tincidunt. Nulla facilisi. Sed ut posuere libero, in blandit dolor. Fusce non bibendum justo, ac tincidunt nulla. Fusce eget odio arcu. Vestibulum ac dolor a nisi egestas bibendum. Suspendisse potenti. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed pellentesque facilisis vestibulum. In fringilla est at tincidunt. Vivamus euismod ex vel dolor varius, quis vehicula odio fermentum."
    },
    {
        user: "carol",
        text: "Praesent id aliquam leo. Vivamus eget nunc a libero commodo auctor. Sed sit amet tortor et arcu tincidunt congue vel at ex. Ut euismod urna nec libero volutpat, vel rhoncus purus interdum. Sed ac odio nec metus ullamcorper pellentesque. Sed vel eros eu libero consequat suscipit ut ac enim. In scelerisque ante eu lorem vestibulum, quis feugiat velit fringilla. Etiam volutpat varius neque, eu tincidunt arcu blandit vel. Nullam quis bibendum lectus, sit amet bibendum urna. Vestibulum a odio eget libero faucibus convallis in nec libero. Aliquam sed metus a urna varius volutpat."
    },
    {
        user: "David",
        text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod lacus ut odio auctor, a commodo nulla efficitur. Integer facilisis eros eu velit interdum, at varius tortor bibendum. In et ante eu metus bibendum bibendum. Vivamus vel tincidunt arcu. Suspendisse at vehicula sapien, a aliquet libero. Nulla facilisi. Maecenas bibendum metus sit amet ligula viverra, vel aliquam dolor sollicitudin. Fusce varius tristique eros, a consequat justo. Sed eu vestibulum lorem. Sed feugiat, urna at commodo mattis, lorem quam efficitur metus, vel placerat justo orci nec mi."
    },
    {
        user: "Eva",
        text: "Vestibulum sit amet euismod dolor. Proin hendrerit tortor eget ex vulputate, in vulputate nulla tincidunt. Etiam non ligula at libero ultrices venenatis. Nulla facilisi. In hac habitasse platea dictumst. Etiam hendrerit vestibulum massa, at imperdiet lectus viverra id. Nullam ut efficitur tellus, in fermentum velit. Fusce congue nec orci sit amet cursus. Vivamus a augue vel nulla blandit venenatis. Morbi a mi quam. In tincidunt elit sit amet nulla bibendum, eu lobortis neque laoreet. Cras dapibus risus non tortor rhoncus, et posuere felis scelerisque."
    },
    {
        user: "Frank",
        text: "Suspendisse potenti. Duis efficitur urna id luctus malesuada. Sed facilisis, velit a elementum cursus, odio urna vehicula justo, nec vehicula quam arcu in ex. Phasellus auctor metus vel libero auctor, eu vehicula elit lacinia. Nulla sed scelerisque tortor. Vestibulum sit amet lacus nec orci malesuada auctor. Fusce eget tincidunt dui, non mattis ligula. Pellentesque ac purus nec tellus vehicula volutpat. Nulla facilisi. Curabitur facilisis, neque ut semper facilisis, lectus eros fringilla erat, eu scelerisque ipsum est eget nunc."
    },
    {
        user: "Grace",
        text: "odio id rhoncus vulputate, ex massa pellentesque quam, nec pellentesque justo urna ac justo. Praesent cursus odio eu erat euismod, id sollicitudin augue fermentum. Curabitur laoreet augue ut odio hendrerit, non tincidunt quam scelerisque. Nam ultrices nisi eget augue tempus, eget suscipit quam aliquet. Vivamus dictum euismod orci, sit amet egestas tortor luctus in. Nulla facilisi. Fusce non tristique ex. Fusce nec magna eget dui iaculis congue."
    },
];
export default function Posts() {
    const postText = useRef<HTMLTextAreaElement>(null)
    const viewingUser = false
    function sendPost() {
        console.log(postText.current?.value)
    }
    return (
        <>
            {viewingUser ? null : <SendPost />}
            {posts.map(({ user, text }) => <Post text={text} user={user} />)}

            <button className="btn btn-primary w-full mb-12">Load More Posts</button>
        </>
    )
}