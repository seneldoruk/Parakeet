import { zodResolver } from "@hookform/resolvers/zod";
import { MetaFunction } from "@remix-run/react";
import React from "react";
import { SubmitHandler, useForm } from "react-hook-form";
import { z } from "zod";

export const meta: MetaFunction = () => {
    return [
        { title: "Parakeet | Login" }
    ];
};

const Login = () => {
    const formSchema = z.object({
        username: z.string()
            .min(5, "Must be at least 5 characters")
            .max(15, "Must be maximum 15 characters"),
        password: z.string()
            .min(8, { message: "Password must be at least 8 characters long" })
            .regex(/[0-9]/, { message: "Password must contain at least one number" }),
        loginOrRegister: z.enum(["login", "register"])
    })
    type FormSchema = z.infer<typeof formSchema>

    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<FormSchema>({
        resolver: zodResolver(formSchema)
    });

    const onSubmit: SubmitHandler<FormSchema> = ({ username, password, loginOrRegister }) => {
        const isLoginForm = loginOrRegister == "login"
    };
    return (
        <div className="flex w-full justify-center items-center min-h-screen">
            <form className="flex justify-center items-center w-2/5 min-h-screen bg-white px-5 py-5" onSubmit={handleSubmit(onSubmit)}>
                <div className="xl:max-w-7xl bg-white drop-shadow-xl border border-black/20 w-full rounded-md flex justify-between items-stretch px-5 xl:px-5 py-5">
                    <div className="mx-auto w-full lg:w-1/2 md:p-10 py-5 md:py-0">
                        <h1 className="text-center text-2xl sm:text-3xl font-semibold text-[#4A07DA]">
                            Auth
                        </h1>
                        <div className="w-full mt-5 sm:mt-8">
                            <div className="mx-auto w-full sm:max-w-md md:max-w-lg flex flex-col gap-5 items-center">
                                <input
                                    type="text"
                                    placeholder="Username"
                                    className="input input-bordered input-primary w-full text-black placeholder:text-black/70"
                                    {...register("username")}
                                />
                                {errors.username &&
                                    <div className="alert alert-error">
                                        <span>{errors.username.message}</span>

                                    </div>
                                }
                                <input
                                    type="Password"
                                    placeholder="Password"
                                    className="input input-bordered input-primary w-full text-black placeholder:text-black/70"
                                    {...register("password")}
                                />
                                {errors.password &&
                                    <div className="alert alert-error">
                                        <span>{errors.password.message}</span>
                                    </div>
                                }
                                <div className="form-control w-full ">
                                    <label className="label cursor-pointer">
                                        <span className="label-text">Register</span>
                                        <input type="radio" className="radio checked:bg-red-500"
                                            value="register"
                                            {...register("loginOrRegister")}
                                        />
                                    </label>
                                    <label className="label cursor-pointer">
                                        <span className="label-text">Login</span>
                                        <input type="radio" className="radio checked:bg-blue-500" checked
                                            value="login"
                                            {...register("loginOrRegister")}
                                        />
                                    </label>
                                </div>

                                <button className="btn btn-active btn-primary btn-block">
                                    Enter
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default Login

