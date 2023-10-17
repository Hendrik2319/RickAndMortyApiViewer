import '../App.css';

type Props = {
    name: string
    value: any
};

export default function ValueOutputField( props:Props ) {
    return (
        <div className="ValueOutputField">
            <div className="Name" >{props.name }</div>
            <div className="Value">{props.value}</div>
        </div>
    )
}