import React, { useEffect } from "react";
import { Routes, Route } from "react-router-dom";
import "./App.css";
import Navbar from "./components/Navbar";
import Sidebar from "./components/Sidebar";

import state from "./stateManager";
import { useAtom } from "jotai";
import fetch from "./fetch";
import Register from "./components/Register";
import Feed from "./components/Feed";

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
                <Routes>
                    <Route path="/register" element={<Register />} />
                    <Route path="/" element={<Feed />} />
                </Routes>
            </div>
        </div>
    );
};

export default App;
