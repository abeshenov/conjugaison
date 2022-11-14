import { DocumentNode, gql, useQuery } from "@apollo/client"
import { Autocomplete, TextField } from "@mui/material"
import { useState } from "react"
import { Conjugation } from "../components/Conjugation"

const verbListQuery: DocumentNode = gql`
    {
        verbs
    }`

export const ConjugationPage: () => JSX.Element =
    () => {
        const { data, loading, error } = useQuery(verbListQuery)
        const [verb, setVerb] = useState<string | null>("être")

        if (loading) {
            return <p>"Loading..."</p>
        }

        if (error != null) {
            return <pre>{error.message}</pre>
        }

        const verbs: string[] = data.verbs

        return (
            <>
                <Autocomplete
                    disablePortal
                    id="combo-box-demo"
                    options={verbs}
                    sx={{ width: 300 }}
                    renderInput={(params) => <TextField {...params} label="Verb" />}
                    onChange={(_, val) => setVerb(val)}
                />

                { verb != null ? <Conjugation verb={verb} /> : <></> }
            </>
        )
    }
