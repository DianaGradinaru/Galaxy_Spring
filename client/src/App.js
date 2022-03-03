import React, { useEffect } from "react";
import "./App.css";
import Navbar from "./components/Navbar";
import Sidebar from "./components/Sidebar";
import Star from "./components/Star";
import SendStar from "./components/SendStar";
import state from "./stateManager";
import { useAtom } from "jotai";
import fetch from "./fetch";

const App = () => {
    const [stars, setStars] = useAtom(state.cardsAtom);

    // const fetchUsers = async () => {
    //     const request = await fetch();
    //     const response = await request.json();
    //     console.log(response);
    // };

    useEffect(() => {
        const fetchStars = async () => {
            const response = await fetch.get(
                "http://localhost:8080/api/v1/galaxies"
            );
            if (response) {
                setStars(response.sort((a, b) => b.id - a.id));
            }
        };
        fetchStars();
    }, []);

    return (
        <div className="App">
            <Navbar />
            <div className="container mt-5 pt-1">
                <div className="row">
                    <Sidebar />
                    <div className="col-md-9">
                        <SendStar />
                        {stars.map((s) => (
                            <Star key={s.id} {...s} />
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default App;
