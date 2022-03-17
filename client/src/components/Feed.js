import Star from "./Star";
import SendStar from "./SendStar";
import state from "../stateManager";
import { useAtom } from "jotai";
import fetch from "../fetch";
import { useEffect } from "react";
import Sidebar from "./Sidebar";

const Feed = () => {
    const [stars, setStars] = useAtom(state.cardsAtom);

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
        <div className="row">
            <Sidebar />
            <div className="col-md-9">
                <SendStar />
                {stars.map((s) => (
                    <Star key={s.id} {...s} />
                ))}
            </div>
        </div>
    );
};

export default Feed;
