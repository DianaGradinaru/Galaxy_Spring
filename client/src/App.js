import React, { useEffect } from "react";
import "./App.css";
import Navbar from "./components/Navbar";
import Sidebar from "./components/Sidebar";
import Star from "./components/Star";
import state from "./stateManager";
import { useAtom } from "jotai";

const App = () => {
    const [stars, setStars] = useAtom(state.cardsAtom);

    const fetchUsers = async () => {
        const request = await fetch();
        const response = await request.json();
        console.log(response);
    };

    useEffect(() => {
        const fetchStars = async () => {
            const request = await fetch(
                "http://localhost:8080/api/v1/galaxies"
            );
            const response = await request.json();
            if (response) {
                setStars(response);
            }
        };

        fetchStars();
    }, []);

    return (
        <div className="App">
            <Navbar />
            <div className="container mt-5 pt-1">
                <div className="row mt-5 ">
                    <Sidebar />
                    <div className="col-md-9">
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
