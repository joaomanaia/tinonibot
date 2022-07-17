import { Box, Container, Typography } from "@mui/material"
import type { NextPage } from "next"
import Head from "next/head"

const Home: NextPage = () => {
  return (
    <Container maxWidth="lg">
      <Head>
        <title>TinoniBot</title>
        <meta name="description" content="The best discord bot!" />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <Box className="my-4 flex flex-col justify-center items-center">
        <Typography variant="h4" component="h1" gutterBottom>
          MUI v5 + Next.js + TypeScript + Tailwidcss
        </Typography>
      </Box>
    </Container>
  )
}

export default Home
