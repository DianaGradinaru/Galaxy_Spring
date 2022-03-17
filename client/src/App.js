import React, { useEffect } from "react";
import { Routes, Route } from "react-router-dom";
import "./App.css";
import Navbar from "./components/Navbar";

import state from "./stateManager";
import { useAtom } from "jotai";
import fetch from "./fetch";
import Register from "./components/Register";
import Feed from "./components/Feed";

const App = () => {
    const [stars, setStars] = useAtom(state.cardsAtom);

    useEffect(() => {
        const fetchStars = async () => {
            const response = await fetch.get(
                process.env.REACT_APP_SERVER_ALL_GALAXIES
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
