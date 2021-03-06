package de.unidue.ltl.ctest.difficulty.features.util;

import java.io.IOException;

public class PhonetisaurusPronunciation
{
    private String inputWord;
    private String pronunciation;
    private Double phoneticScore;

    public PhonetisaurusPronunciation(String phonetisaurusOutput)
        throws IOException
    {

        String[] output = phonetisaurusOutput.split("\t");
        if (output.length == 3) {
            inputWord = output[0];
            phoneticScore = Double.parseDouble(output[1]);
            pronunciation = output[2];
        }
        else if (output.length == 2) {
            phoneticScore = Double.parseDouble(output[0]);
            pronunciation = output[1];
        }
        else {
            throw new IOException("Wrong input format: " + phonetisaurusOutput);
        }
    }

    public String getInputWord()
    {
        return inputWord;
    }

    public String getPronunciation()
    {
        return pronunciation;
    }

    public Double getPhoneticScore()
    {
        return phoneticScore;
    }
}