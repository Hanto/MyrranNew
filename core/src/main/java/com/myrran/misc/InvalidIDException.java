package com.myrran.misc;

/** @author Ivan Delgado Huerta */
public class InvalidIDException extends Exception
{
    public InvalidIDException(String errorCause)
    {   super(errorCause); }

    public InvalidIDException(String errorCause, Throwable throwable)
    {   super(errorCause, throwable); }

    public InvalidIDException(String errorCause, Object...params)
    {   super(String.format(errorCause, params)); }

    public InvalidIDException(String errorCause, Throwable throwable, Object...params)
    {   super(String.format(errorCause, params), throwable); }
}
