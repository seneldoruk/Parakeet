import stylesheet from "~/tailwind.css";
import { redirect, type LinksFunction } from "@remix-run/node";
import { Provider } from "react-redux";
import toast, { Toaster } from 'react-hot-toast';


import {
  Links,
  LiveReload,
  Meta,
  MetaFunction,
  Outlet,
  Scripts,
  ScrollRestoration,
} from "@remix-run/react";
import Navbar from "./components/navbar";
import { store } from "./state/store";

export const links: LinksFunction = () => [
  { rel: "stylesheet", href: stylesheet },
];

export const meta: MetaFunction = () => {
  return [
    { title: "Parakeet" },
  ];
};


export default function App() {
  return (
    <html lang="en">
      <head>
        <meta charSet="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <Meta />
        <Links />
      </head>
      <body className="w-max-screen overflow-x-hidden ">
        <Provider store={store}>
          <Outlet />
          <Toaster />
        </Provider>
        <ScrollRestoration />
        <Scripts />
        <LiveReload />
      </body>
    </html>
  );
}
